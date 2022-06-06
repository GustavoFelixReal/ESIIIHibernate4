package persistence;

import java.time.LocalDate;
import java.util.List;

import model.Atendimento;

public interface IAtendimentoDao {
  public void insere(Atendimento atend);

  public Atendimento selectOne(String cpf, String idAtend, LocalDate data);

  public List<Atendimento> selectOneCliente(String cpf);

  public List<Atendimento> selectOneAtendente(String idAtend);

  public List<Atendimento> selectAll();
}
