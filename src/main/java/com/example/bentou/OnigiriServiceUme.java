package com.example.bentou;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ume")
public class OnigiriServiceUme implements OnigiriService {
    private final String guzai = "梅";

    @Override
    public Onigiri provideOnigiri() {
        Kome kome = new Kome("あきたこまち");
        return new Onigiri(kome, guzai);
    }
}
