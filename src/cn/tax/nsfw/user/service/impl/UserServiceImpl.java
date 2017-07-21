package cn.tax.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import cn.tax.core.action.service.impl.BaseServiceImpl;
import cn.tax.core.util.ExeclUtil;
import cn.tax.nsfw.role.entity.Role;
import cn.tax.nsfw.user.dao.UserDao;
import cn.tax.nsfw.user.entity.User;
import cn.tax.nsfw.user.entity.UserRole;
import cn.tax.nsfw.user.entity.UserRoleId;
import cn.tax.nsfw.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	
	private UserDao userDao;
	
	
	@Resource 
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}


	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
		//删除用户对应的所有权限
		userDao.deleteUserRoleByUserId(id);
	}

	
	@Override
	public  void exportExcel(List<User> userList,
			ServletOutputStream outputStream) 
	{
		ExeclUtil.exportUserExcel(userList, outputStream);
	}
	@Override
	public void importExcel(File userExcel,String userExcelFileName)
	{
		try {
			FileInputStream fileInputStream = new FileInputStream(userExcel);
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			//1、读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream)
					: new XSSFWorkbook(fileInputStream);
			//2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			//3、读取行
			if(sheet.getPhysicalNumberOfRows()>2)
			{
				User user = null;
				for(int k=2;k<sheet.getPhysicalNumberOfRows();k++)
				{
					//4、读取单元格
					Row row = sheet.getRow(k);
					user = new User();
					//用户名
					Cell cell=row.getCell(0);					
					user.setName(cell.getStringCellValue());
					//账号
					Cell cell1=row.getCell(1);					
					user.setAccount(cell1.getStringCellValue());
					//所属部门
					Cell cell2=row.getCell(2);					
					user.setDept(cell2.getStringCellValue());
					//性别
					Cell cell3=row.getCell(3);					
					user.setGender(cell3.getStringCellValue().equals("男"));
					//手机号
					String mobile = "";
					Cell cell4=row.getCell(4);	
					try {
						mobile = cell4.getStringCellValue();
					} catch (Exception e) {
						// TODO: handle exception
						double dMobile = cell4.getNumericCellValue();
						 mobile = BigDecimal.valueOf(dMobile).toString();
					}
					user.setMobile(mobile);
					//电子邮箱
					Cell cell5=row.getCell(5);					
					user.setEmail(cell5.getStringCellValue());
					//生日
					Cell cell6=row.getCell(6);					
					if(cell6.getDateCellValue()!=null)
					{
						user.setBirthday(cell6.getDateCellValue());
					}
				
					//默认用户密码为1234
					user.setPassword("123456");
					//默认用户状态有效
					user.setState(User.USER_STATE_VALID);
					//5、保存用户
					save(user);
				}
			}
			workbook.close();
			fileInputStream.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		// TODO Auto-generated method stub
		
		return userDao.findUserByAccountAndId(id, account);
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		// TODO Auto-generated method stub
		//1、保存用户
		save(user);
		//2、保存用户对应的角色
		if(roleIds!=null)
		{
			for(String roleId:roleIds)
			{
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		// TODO Auto-generated method stub
		//1、根据用户删除用户的所有角色
		userDao.deleteUserRoleByUserId(user.getId());
		//2、更新用户
		update(user);
		//3、保存用户对应的角色
		if(roleIds!=null)
		{
			for(String roleId:roleIds)
			{
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public List<UserRole> getUserRoleByUserId(String id) {
		// TODO Auto-generated method stub
		return userDao.getUserRoleByUserId(id);
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		// TODO Auto-generated method stub
		
		return userDao.findUserByAccountAndPass(account,password);
	}
}
