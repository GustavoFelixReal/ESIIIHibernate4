package persistence;

import java.util.List;

import model.Entregador;

public class EntregdorDao implements IObjDao<Entregador> {
	
	private SessionFactory sf;
	
	public EntregdorDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insere(Entregador en) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(en);
		transaction.commit();		
	}

	@Override
	public void modifica(Entregador en) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(en);
		transaction.commit();		
	}

	@Override
	public void remove(Entregador en) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(en);
		transaction.commit();		
	}

	@Override
	public Entregador busca(Entregador en) {
		EntityManager entityManager = sf.createEntityManager();
		en = entityManager.find(Entregador.class, en.getId());
		return en;
	}

	@Override
	public List<Entregador> lista() {
		List<Entregador> entregadores = new ArrayList<Entregador>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT f.*, e.num_cnh, e.categoria_cnh ");
		buffer.append("FROM entregador e ");
		buffer.append("LEFT JOIN funcionario f ON f.id = e.id");
		buffer.append("ORDER BY e.id");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Entregador en = new Entregador();
			en.setId(Integer.parseInt(obj[0]));
			en.setNome(obj[1].toString());
			en.setDataNascimento(obj[2].toString());
			en.setSalario(Float.parseFloat(obj[3]));
			en.setTelefone(obj[4].toString());
			en.setNumCnh(obj[5].toString());
			en.setCategoriaCnh(obj[6].toString());
			
			entregadores.add(en);
		}
		
		return entregadores;
	}

}
