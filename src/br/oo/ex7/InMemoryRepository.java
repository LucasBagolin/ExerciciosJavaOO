package br.oo.ex7;

import java.util.*;

public class InMemoryRepository<T extends Identificavel<ID>, ID> implements IRepository<T, ID> {

    private final Map<ID, T> storage = new HashMap<>();

    @Override
    public void salvar(T entidade) {
        if (entidade == null) throw new IllegalArgumentException("Entidade não pode ser nula.");
        ID id = entidade.getId();
        if (id == null) throw new IllegalArgumentException("ID da entidade não pode ser nulo.");
        storage.put(id, entidade); // upsert
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> listarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(storage.values()));
    }

    @Override
    public void remover(ID id) throws EntidadeNaoEncontradaException {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        if (!storage.containsKey(id)) {
            throw new EntidadeNaoEncontradaException("Entidade com ID " + id + " não encontrada.");
        }
        storage.remove(id);
    }
}