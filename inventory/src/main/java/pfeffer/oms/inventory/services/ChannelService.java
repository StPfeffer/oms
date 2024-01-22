package pfeffer.oms.inventory.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfeffer.oms.inventory.domain.dtos.ChannelDTO;
import pfeffer.oms.inventory.domain.mappers.ChannelMapper;
import pfeffer.oms.inventory.domain.repositories.IChannelRepository;
import pfeffer.oms.inventory.domain.usecases.CreateChannel;
import pfeffer.oms.inventory.jakarta.mappers.JakartaChannelMapper;
import pfeffer.oms.inventory.jakarta.model.JakartaChannel;
import pfeffer.oms.inventory.jakarta.repository.JakartaChannelRepository;

import java.util.List;

@Service
public class ChannelService implements IChannelRepository {

    private final JakartaChannelRepository repository;

    @Autowired
    public ChannelService(JakartaChannelRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ChannelDTO createChannel(ChannelDTO dto) {
        CreateChannel createChannel = new CreateChannel(repository);

        return createChannel.execute(dto);
    }

    public List<ChannelDTO> listChannels() {
        return this.repository.findAll().stream().map(jakartaChannel ->
                ChannelMapper.toDTO(JakartaChannelMapper.toDomain(jakartaChannel))
        ).toList();
    }

    @Override
    public ChannelDTO findChannelByChannelId(String channelId) {
        ChannelDTO channel = this.repository.findChannelByChannelId(channelId);

        if (channel == null) {
            throw new RuntimeException("Não existe um canal para o ID informado");
        }

        return channel;
    }

}
