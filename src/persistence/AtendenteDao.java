package persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Atendente;

public class AtendenteDao implements IObjDao<Atendente> {

	private SessionFactory sf;

	public AtendenteDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insere(Atendente at) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(at);
		transaction.commit();
	}

	@Override
	public void modifica(Atendente at) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(at);
		transaction.commit();
	}

	@Override
	public void remove(Atendente at) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(at);
		transaction.commit();

	}

	@Override
	public Atendente busca(Atendente at) {
		EntityManager entityManager = sf.createEntityManager();
		at = entityManager.find(Atendente.class, at.getId());
		return at;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendente> lista() {
		List<Atendente> atendentes = new ArrayList<Atendente>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT f.*, a.hora_entrada, a.hora_saida, a.email ");
		buffer.append("FROM atendente a ");
		buffer.append("LEFT JOIN funcionario f ON f.id = a.id ");
		buffer.append("ORDER BY a.id");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Atendente at = new Atendente();
			at.setId(Integer.parseInt(obj[0].toString()));
			at.setDataNascimento(LocalDate.parse(obj[1].toString()));
			at.setNome(obj[2].toString());
			at.setSalario(Float.parseFloat(obj[3].toString()));
			at.setTelefone(obj[4].toString());
			at.setHoraEntrada(Integer.parseInt(obj[5].toString()));
			at.setHoraSaida(Integer.parseInt(obj[6].toString()));
			at.setEmail(obj[7].toString());

			atendentes.add(at);
		}

		return atendentes;
	}

}
