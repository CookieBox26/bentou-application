package com.example.bentou;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("tuna")
public class OnigiriServiceTuna implements OnigiriService {
    private final KomeRepository komeRepository;

    public OnigiriServiceTuna(KomeRepository komeRepository) {
        this.komeRepository = komeRepository;
    }

    @Override
    public Onigiri provideOnigiri() {
        // KomeRepository.Kome kome = this.komeRepository.getKome();
        return new Onigiri("ツナ");
    }
}
