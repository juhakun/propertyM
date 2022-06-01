package de.muulti.spring.entity;

public interface ObjectActions extends MySQLConnection {

	public void show(Object o);

	public void change(Object o);

	public void delete(Object o);

}
