package persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Atendente;
import model.Atendimento;
import model.Cliente;

public class AtendimentoDao implements IAtendimentoDao {

  private SessionFactory sf;

  public AtendimentoDao(SessionFactory sf) {
    this.sf = sf;
  }

  @Override
  public void insere(Atendimento atend) {
    EntityManager entityManager = sf.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(atend);
    transaction.commit();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Atendimento selectOne(String cpf, String idAtend, LocalDate data) {
    Atendimento at = new Atendimento();

    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT at.data_hora_atendimento, c.*, f.*, a.hora_entrada, a.hora_saida, a.email ");
    buffer.append("FROM atendimento at ");
    buffer.append("LEFT JOIN atendente a ON a.id = at.id_atendente ");
    buffer.append("LEFT JOIN cliente c ON c.cpf_cliente = at.cpf_cliente ");
    buffer.append("LEFT JOIN funcionario f ON f.id = a.id ");
    buffer.append("WHERE at.id_atendente = " + idAtend);
    buffer.append(" at.cpf_cliente = '" + cpf + "'");
    buffer.append(" at.data_hora_atendimento = '" + data.toString() + "'");
    buffer.append(" ORDER BY a.id");
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());
    List<Object[]> lista = query.getResultList();

    for (Object[] obj : lista) {
      Cliente cli = new Cliente();

      cli.setCpf(obj[1].toString());
      cli.setCelular(obj[2].toString());
      cli.setEmail(obj[3].toString());
      cli.setNome(obj[4].toString());
      cli.setPronomeTratamento(obj[5].toString());

      Atendente a = new Atendente();

      a.setId(Integer.parseInt(obj[6].toString()));
      a.setDataNascimento(LocalDate.parse(obj[7].toString()));
      a.setNome(obj[8].toString());
      a.setSalario(Float.parseFloat(obj[9].toString()));
      a.setTelefone(obj[10].toString());
      a.setHoraEntrada(Integer.parseInt(obj[11].toString()));
      a.setHoraSaida(Integer.parseInt(obj[12].toString()));
      a.setEmail(obj[13].toString());

      at.setAtendente(a);
      at.setCliente(cli);
      at.setDataHora(LocalDateTime.parse(obj[0].toString()));
    }

    return at;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Atendimento> selectOneCliente(String cpf) {
    List<Atendimento> ats = new ArrayList<Atendimento>();

    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT at.data_hora_atendimento, c.*, f.*, a.hora_entrada, a.hora_saida, a.email ");
    buffer.append("FROM atendimento at ");
    buffer.append("LEFT JOIN atendente a ON a.id = at.id_atendente ");
    buffer.append("LEFT JOIN cliente c ON c.cpf_cliente = at.cpf_cliente ");
    buffer.append("LEFT JOIN funcionario f ON f.id = a.id ");
    buffer.append("WHERE at.cpf_cliente = '" + cpf + "'");
    buffer.append(" ORDER BY a.id");
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());
    List<Object[]> lista = query.getResultList();

    for (Object[] obj : lista) {
      Cliente cli = new Cliente();

      cli.setCpf(obj[1].toString());
      cli.setCelular(obj[2].toString());
      cli.setEmail(obj[3].toString());
      cli.setNome(obj[4].toString());
      cli.setPronomeTratamento(obj[5].toString());

      Atendente a = new Atendente();

      a.setId(Integer.parseInt(obj[6].toString()));
      a.setDataNascimento(LocalDate.parse(obj[7].toString()));
      a.setNome(obj[8].toString());
      a.setSalario(Float.parseFloat(obj[9].toString()));
      a.setTelefone(obj[10].toString());
      a.setHoraEntrada(Integer.parseInt(obj[11].toString()));
      a.setHoraSaida(Integer.parseInt(obj[12].toString()));
      a.setEmail(obj[13].toString());

      Atendimento at = new Atendimento();

      at.setAtendente(a);
      at.setCliente(cli);
      at.setDataHora(LocalDateTime.parse(obj[0].toString()));

      ats.add(at);
    }

    return ats;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Atendimento> selectOneAtendente(String idAtend) {
    List<Atendimento> ats = new ArrayList<Atendimento>();

    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT at.data_hora_atendimento, c.*, f.*, a.hora_entrada, a.hora_saida, a.email ");
    buffer.append("FROM atendimento at ");
    buffer.append("LEFT JOIN atendente a ON a.id = at.id_atendente ");
    buffer.append("LEFT JOIN cliente c ON c.cpf_cliente = at.cpf_cliente ");
    buffer.append("LEFT JOIN funcionario f ON f.id = a.id ");
    buffer.append("WHERE at.id_atendente = " + idAtend);
    buffer.append(" ORDER BY a.id");
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());
    List<Object[]> lista = query.getResultList();

    for (Object[] obj : lista) {
      Cliente cli = new Cliente();

      cli.setCpf(obj[1].toString());
      cli.setCelular(obj[2].toString());
      cli.setEmail(obj[3].toString());
      cli.setNome(obj[4].toString());
      cli.setPronomeTratamento(obj[5].toString());

      Atendente a = new Atendente();

      a.setId(Integer.parseInt(obj[6].toString()));
      a.setDataNascimento(LocalDate.parse(obj[7].toString()));
      a.setNome(obj[8].toString());
      a.setSalario(Float.parseFloat(obj[9].toString()));
      a.setTelefone(obj[10].toString());
      a.setHoraEntrada(Integer.parseInt(obj[11].toString()));
      a.setHoraSaida(Integer.parseInt(obj[12].toString()));
      a.setEmail(obj[13].toString());

      Atendimento at = new Atendimento();

      at.setAtendente(a);
      at.setCliente(cli);
      at.setDataHora(LocalDateTime.parse(obj[0].toString()));

      ats.add(at);
    }

    return ats;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Atendimento> selectAll() {
    List<Atendimento> ats = new ArrayList<Atendimento>();

    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT at.data_hora_atendimento, c.*, f.*, a.hora_entrada, a.hora_saida, a.email ");
    buffer.append("FROM atendimento at ");
    buffer.append("LEFT JOIN atendente a ON a.id = at.id_atendente ");
    buffer.append("LEFT JOIN cliente c ON c.cpf_cliente = at.cpf_cliente ");
    buffer.append("LEFT JOIN funcionario f ON f.id = a.id ");
    buffer.append("ORDER BY a.id");
    EntityManager entityManager = sf.createEntityManager();
    Query query = entityManager.createNativeQuery(buffer.toString());
    List<Object[]> lista = query.getResultList();

    for (Object[] obj : lista) {
      Cliente cli = new Cliente();

      cli.setCpf(obj[1].toString());
      cli.setCelular(obj[2].toString());
      cli.setEmail(obj[3].toString());
      cli.setNome(obj[4].toString());
      cli.setPronomeTratamento(obj[5].toString());

      Atendente a = new Atendente();

      a.setId(Integer.parseInt(obj[6].toString()));
      a.setDataNascimento(LocalDate.parse(obj[7].toString()));
      a.setNome(obj[8].toString());
      a.setSalario(Float.parseFloat(obj[9].toString()));
      a.setTelefone(obj[10].toString());
      a.setHoraEntrada(Integer.parseInt(obj[11].toString()));
      a.setHoraSaida(Integer.parseInt(obj[12].toString()));
      a.setEmail(obj[13].toString());

      Atendimento at = new Atendimento();

      at.setAtendente(a);
      at.setCliente(cli);
      at.setDataHora(LocalDateTime.parse(obj[0].toString()));

      ats.add(at);
    }

    return ats;
  }

}
