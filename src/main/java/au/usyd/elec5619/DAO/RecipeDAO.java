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
	
	//添加
	public void addRecipe(Recipe recipe) {
		this.getSession().save(recipe);
		//int id = recipe.getrecipeID();
		//System.out.println(id);
//		Transaction transaction=null;
//		transaction=session.beginTransaction();
//		session.save(recipe);
//		transaction.commit();
	}
	
	//按照id查询
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
	
	//更新
	public void updateRecipe(Recipe recipe) {
		this.getSession().update(recipe);
	}
	
	//删除
	public void deleteRecipe(int recipeID) {
		this.getSession().createQuery("delete Step where recipeID=?").setParameter(0, recipeID).executeUpdate();
		this.getSession().createQuery("delete Ingredient where recipeID=?").setParameter(0, recipeID).executeUpdate();
		this.getSession().createQuery("delete Recipe where id=?").setParameter(0, recipeID).executeUpdate();
	}
	
	//得到所有菜谱
	public List<Recipe> getallrecipes() {
		return this.sessionFactory.getCurrentSession().createQuery("FROM Recipe").list();
	}
	
	//按类别获取
	public List<Recipe> getrecipebycategory(int categoryid){
		return this.getSession().createQuery("from Recipe where categoryID=?").setInteger(0, categoryid).list();
	}
	
	//按userID获取
	public List<Recipe> getrecipebyuser(int userID){
		return this.getSession().createQuery("from Recipe where userID=?").setInteger(0, userID).list();
	}
	//按时间获取
	public List<Recipe> getrecipebycooktime(int cooktime){
		return this.getSession().createQuery("from Recipe where cookTime<=?").setInteger(0, cooktime).list();
	}
	//按时间和类别获取
	public List<Recipe> getrecipebytimeandtype(int categoryID, int cookTime){
		return this.getSession().createQuery("from Recipe where (categoryID=? and cookTime<=?)").setInteger(0, categoryID).setInteger(1, cookTime).list();
	}

}
