package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImp implements CarDao {

  private SessionFactory sessionFactory;

  @Autowired
  public CarDaoImp(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addCar(Car car) {
    sessionFactory.getCurrentSession().save(car);
  }

  @Override
  @SuppressWarnings("unchecked")
  public User getUserByCar(String model, int series) {
    User user;
    Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.model=:model and u.car.series=:series");
    query.setParameter("model", model);
    query.setParameter("series", series);
    user = (User) query.getSingleResult();
    return user;
  }
}
