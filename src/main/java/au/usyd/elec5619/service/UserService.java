package au.usyd.elec5619.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.DAO.*;
import au.usyd.elec5619.service.*;

@Transactional
@Service(value = "usermanager")
public class UserService implements Usercreater {

	@Autowired
	public UserDAO userDAO;

	// ע��
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	//��¼
	public int logincheck(String email, String pwd) {
		User user = userDAO.getuserbyemail(email);
		if(user==null) {
			System.out.println("not exist");
			//�û�������
			return 1;
		}
		else if(pwd.equals(user.getPassword())) {
			//��¼�ɹ�
			System.out.println("success");
			return 2;
		}
		else {
			//���벻ƥ��
			System.out.println("password fail");
			return 0;
		}
	}
	//��email��user
	public User getuserbyemail(String email) {
		User user = userDAO.getuserbyemail(email);
		return user;
	}

	public User getUserById(int id) {
		User user = userDAO.getUserById(id);
		return user;
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public void deleteUserById(String id) {
		userDAO.deleteUserById(id);
	}

	public List<User> getallusers() {
		List<User> userlist = userDAO.getallusers();
		System.out.println("1111");
		return userlist;
	}


	@Override
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

}
