package com.example.bentou;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ume")
public class OnigiriServiceUme implements OnigiriService {
    @Override
    public Onigiri provideOnigiri() {
        return new Onigiri("梅");
    }
}
