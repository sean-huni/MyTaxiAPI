package com.mytaxi.service.security.impl;

import com.mytaxi.dataaccessobject.PersistentRememberMeTokenRepo;
import com.mytaxi.domainobject.PersistentRememberMeTokenDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.service.auth.impl
 * USER      : sean
 * DATE      : 04-Thu-Oct-2018
 * TIME      : 00:22
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class PersistentTokenDefaultImpl implements PersistentTokenRepository {
    private PersistentRememberMeTokenRepo rememberMeTokenRepo;

    @Autowired
    public PersistentTokenDefaultImpl(PersistentRememberMeTokenRepo rememberMeTokenRepo) {
        this.rememberMeTokenRepo = rememberMeTokenRepo;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentRememberMeTokenDO tokenDO = new PersistentRememberMeTokenDO();

        tokenDO.setUsername(token.getUsername());
        tokenDO.setSeries(token.getSeries());
        tokenDO.setTokenValue(token.getTokenValue());
        tokenDO.setDate(token.getDate());

        rememberMeTokenRepo.save(tokenDO);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {

        Optional<PersistentRememberMeTokenDO> rememberMeToken = rememberMeTokenRepo.findBySeries(series);

        if (rememberMeToken.isPresent()) {
            PersistentRememberMeTokenDO tokenDO = rememberMeToken.get();
            tokenDO.setSeries(tokenValue);
            tokenDO.setDate(lastUsed);
            rememberMeTokenRepo.save(tokenDO);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        Optional<PersistentRememberMeTokenDO> rememberMeToken = rememberMeTokenRepo.findBySeries(seriesId);
        if (rememberMeToken.isPresent()) {
            PersistentRememberMeTokenDO tokenDO = rememberMeToken.get();
            return new PersistentRememberMeToken(tokenDO.getUsername(), tokenDO.getSeries(),
                    tokenDO.getTokenValue(), tokenDO.getDate());
        }
        return new PersistentRememberMeToken("", "", "", null);
    }

    @Override
    public void removeUserTokens(String username) {
        rememberMeTokenRepo.delete(rememberMeTokenRepo.findByUsername(username).get());
    }
}
