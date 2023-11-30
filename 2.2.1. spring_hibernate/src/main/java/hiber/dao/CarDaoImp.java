package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImp implements CarDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void addCar(Car car) {
    sessionFactory.getCurrentSession().save(car);
  }

  @Override
  @SuppressWarnings("unchecked")
  public User getUserByCarModelAndSeries(String model, int series) {
    TypedQuery<User> query = sessionFactory.getCurrentSession()
        .createQuery("from User where car.model = :model and car.series = :series");
    query.setParameter("model", model).setParameter("series", series);
    return query.getSingleResult();
  }

}
