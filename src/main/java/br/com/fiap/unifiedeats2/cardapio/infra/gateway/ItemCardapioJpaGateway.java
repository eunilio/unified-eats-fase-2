package br.com.fiap.unifiedeats2.cardapio.infra.gateway;

import br.com.fiap.unifiedeats2.cardapio.core.domain.ItemCardapio;
import br.com.fiap.unifiedeats2.cardapio.core.gateway.ItemCardapioGateway;
import br.com.fiap.unifiedeats2.cardapio.infra.database.mapper.ItemCardapioEntityMapper;
import br.com.fiap.unifiedeats2.cardapio.infra.database.repository.ItemCardapioRepository;

import java.util.List;
import java.util.Optional;

public class ItemCardapioJpaGateway implements ItemCardapioGateway {

    private final ItemCardapioRepository itemCardapioRepository;

    public ItemCardapioJpaGateway(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio itemCardapio) {
        var entity = ItemCardapioEntityMapper.toEntity(itemCardapio);
        var entitySalva = itemCardapioRepository.save(entity);
        return ItemCardapioEntityMapper.toDomain(entitySalva);
    }

    @Override
    public ItemCardapio atualizar(ItemCardapio itemCardapio) {
        var entity = ItemCardapioEntityMapper.toEntity(itemCardapio);
        var entityAtualizada = itemCardapioRepository.save(entity);
        return ItemCardapioEntityMapper.toDomain(entityAtualizada);
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return itemCardapioRepository.findById(id)
                .map(ItemCardapioEntityMapper::toDomain);
    }

    @Override
    public List<ItemCardapio> listarTodos() {
        return itemCardapioRepository.findAll()
                .stream()
                .map(ItemCardapioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void remover(Long id) {
        itemCardapioRepository.deleteById(id);
    }
}