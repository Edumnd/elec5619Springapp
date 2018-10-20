package au.usyd.elec5619.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import au.usyd.elec5619.domain.Category;
import au.usyd.elec5619.domain.Recipe;
import au.usyd.elec5619.domain.Step;

@Repository
public class RecipeDAO {
	@Resource
	private SessionFactory sessionFactory;
//	Session session=sessionFactory.openSession();
	private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	//���
	public void addRecipe(Recipe recipe) {
		this.getSession().save(recipe);
		//int id = recipe.getrecipeID();
		//System.out.println(id);
//		Transaction transaction=null;
//		transaction=session.beginTransaction();
//		session.save(recipe);
//		transaction.commit();
	}
	
	//����id��ѯ
	public Recipe getrecipebyID(int id) {
		Query query = getSession().createQuery("from Recipe where recipeID=?").setInteger(0, id);
		 List<Recipe> list = query.list();
		 System.out.println(list);
		 //return (Recipe) this.getSession().createQuery("from Recipes where recipeID=?").setParameter(0, id).uniqueResult();
		 return (Recipe) this.getSession().createQuery("from Recipe where recipeID=?").setInteger(0, id).uniqueResult();
	}
	public List<Step> getallstepsforrecipe(int id){
		List<Step> steps = this.getSession().createQuery("from Step where recipeID=?").setInteger(0, id).list();
		return steps;
	}
	
	//����
	public void updateRecipe(Recipe recipe) {
		this.getSession().update(recipe);
	}
	
	//ɾ��
	public void deleteRecipe(int recipeID) {
		this.getSession().createQuery("delete Step where recipeID=?").setParameter(0, recipeID).executeUpdate();
		this.getSession().createQuery("delete Ingredient where recipeID=?").setParameter(0, recipeID).executeUpdate();
		this.getSession().createQuery("delete Recipe where id=?").setParameter(0, recipeID).executeUpdate();
	}
	
	//�õ����в���
	public List<Recipe> getallrecipes() {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Recipe").list();
	}
	
	//������ȡ
	public List<Recipe> getrecipebycategory(int categoryid){
		return this.getSession().createQuery("from Recipe where categoryID=?").setInteger(0, categoryid).list();
	}
	
	//��userID��ȡ
	public List<Recipe> getrecipebyuser(int userID){
		return this.getSession().createQuery("from Recipe where userID=?").setInteger(0, userID).list();
	}

}
