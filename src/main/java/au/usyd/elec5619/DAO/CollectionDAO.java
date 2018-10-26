package au.usyd.elec5619.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import au.usyd.elec5619.domain.Collection;

@Repository
public class CollectionDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	//����ղ�
	public void addcollection(Collection collection) {
		this.getSession().save(collection);
	}
	//ȡ���ղ�
	public void deletecollection(int collectionID) {
		this.getSession().createQuery("delete Collection where id=?").setParameter(0, collectionID).executeUpdate();
	}
	//����user�����ղ�
	public List<Collection> getcollectionbyuser(int userID) {
		return this.getSession().createQuery("from Collection where userID=?").setInteger(0, userID).list();
	}
	public Collection getcollection(int userID,int recipeID) {
		return (Collection) this.getSession().createQuery("from Collection where (userID=? and recipeID=?)").setInteger(0, userID).setInteger(1, recipeID).uniqueResult();
	}
	

}
