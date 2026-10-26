package br.com.sivacon.buyer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sivacon.dao.BuyerAreaDao;
import br.com.sivacon.service.AuthService.UserPrincipal;

@RestController
@RequestMapping("/api/v1/me")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
public class BuyerAreaController {
    private final BuyerAreaDao dao;

    public BuyerAreaController(BuyerAreaDao dao) { this.dao = dao; }

    @GetMapping
    public BuyerArea profile(Authentication authentication) {
        var buyer = dao.findBuyer(userId(authentication));
        if (buyer == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "A conta autenticada não é de comprador.");
        return new BuyerArea(buyer, dao.findInterests(buyerUser(authentication)),
                dao.findProposals(buyerUser(authentication)), dao.findPurchases(buyerUser(authentication)));
    }

    @GetMapping("/interests")
    public List<BuyerAreaDao.InterestRow> interests(Authentication authentication) {
        return dao.findInterests(buyerUser(authentication));
    }

    @GetMapping("/proposals")
    public List<BuyerAreaDao.ProposalRow> proposals(Authentication authentication) {
        return dao.findProposals(buyerUser(authentication));
    }

    @GetMapping("/purchases")
    public List<BuyerAreaDao.PurchaseRow> purchases(Authentication authentication) {
        return dao.findPurchases(buyerUser(authentication));
    }

    private java.util.UUID buyerUser(Authentication a) { return userId(a); }
    private java.util.UUID userId(Authentication a) {
        if (!(a.getPrincipal() instanceof UserPrincipal p) || !"BUYER".equals(p.role())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito ao comprador.");
        }
        return p.id();
    }

    public record BuyerArea(BuyerAreaDao.BuyerRow profile,
                            List<BuyerAreaDao.InterestRow> interests,
                            List<BuyerAreaDao.ProposalRow> proposals,
                            List<BuyerAreaDao.PurchaseRow> purchases) {}
}
