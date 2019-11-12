package com.example.User.API.dao;

import com.example.User.API.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public int insertPerson(UUID id, Person person) {
    final String sqlQuery = "INSERT INTO person VALUES(?, ?)";
    jdbcTemplate.update(sqlQuery, id, person.getName());
    return 1;
  }

  @Override
  public List<Person> selectAllPeople() {
    final String sqlQuery = "SELECT id, name FROM person";
    return jdbcTemplate.query(sqlQuery, (resultSet, i) -> {
      UUID id = UUID.fromString(resultSet.getString("id"));
      String name = resultSet.getString("name");
      return new Person(id, name);
    });
  }

  @Override
  public int deletePersonById(UUID id) {
    final String sqlQuery = "DELETE FROM person WHERE id = ?";
    jdbcTemplate.update(sqlQuery, id);
    return 1;
  }

  @Override
  public int updatePersonById(UUID id, Person person) {
    final String sqlQuery = "UPDATE person SET id = ?, name = ? WHERE id = ?";
    jdbcTemplate.update(sqlQuery, id, person.getName(), id);
    return 1;
  }

  @Override
  public Optional<Person> selectPersonById(UUID id) {
    final String sqlQuery = "SELECT id, name FROM person WHERE id = ?";
    Person person = jdbcTemplate.queryForObject(sqlQuery,
            new Object[]{id},
            (resultSet, i) -> {
      UUID personId = UUID.fromString(resultSet.getString("id"));
      String name = resultSet.getString("name");
      return new Person(personId, name);
    });
    return Optional.ofNullable(person);
  }
}
