package com.example.bentou;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("tuna")
public class OnigiriServiceTuna implements OnigiriService {
    private final KomeRepository komeRepository;
    private final String guzai = "ツナ";

    public OnigiriServiceTuna(
            KomeRepository komeRepository
    ) {
        this.komeRepository = komeRepository;
    }

    @Override
    public Onigiri provideOnigiri() {
        Kome kome = this.komeRepository.getKome();
        return new Onigiri(kome, guzai);
    }
}
