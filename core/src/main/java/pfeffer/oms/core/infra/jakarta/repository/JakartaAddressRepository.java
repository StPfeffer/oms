package pfeffer.oms.core.infra.jakarta.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import pfeffer.oms.core.domain.entities.AddressBO;
import pfeffer.oms.core.domain.repositories.IAddressDataBaseRepository;
import pfeffer.oms.core.infra.jakarta.mappers.JakartaAddressMapper;
import pfeffer.oms.core.infra.jakarta.model.JakartaAddress;

@Service
public class JakartaAddressRepository extends SimpleJpaRepository<JakartaAddress, Long> implements IAddressDataBaseRepository {

    private final EntityManager em;

    @Autowired
    public JakartaAddressRepository(EntityManager em) {
        super(JakartaAddress.class, em);
        this.em = em;
    }

    @Override
    public AddressBO persist(AddressBO bo) {
        JakartaAddress entity = JakartaAddressMapper.toEntity(bo);

        em.persist(entity);
        em.flush();

        return JakartaAddressMapper.toDomain(entity);
    }

    @Override
    public AddressBO update(String id, AddressBO addressBO) {
        throw new UnsupportedOperationException();
    }

}
