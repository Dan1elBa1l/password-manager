package htw.berlin.webtech.CreditCard;

import htw.berlin.webtech.Exceptions.CreditCardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        try {
            List<CreditCard> creditCards = creditCardService.findAll();
            return ResponseEntity.ok(creditCards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Long id) {
        try {
            CreditCard creditCard = creditCardService.get(id);
            return ResponseEntity.ok(creditCard);
        } catch (CreditCardNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        try {
            CreditCard savedCreditCard = creditCardService.save(creditCard);
            return new ResponseEntity<>(savedCreditCard, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCardDetails) {
        try {
            CreditCard existingCreditCard = creditCardService.get(id);
            existingCreditCard.setCardNumber(creditCardDetails.getCardNumber());
            existingCreditCard.setCardHolderName(creditCardDetails.getCardHolderName());
            existingCreditCard.setExpirationDate(creditCardDetails.getExpirationDate());
            existingCreditCard.setCvv(creditCardDetails.getCvv());
            existingCreditCard.setDescription(creditCardDetails.getDescription());
            CreditCard updatedCreditCard = creditCardService.save(existingCreditCard);
            return ResponseEntity.ok(updatedCreditCard);
        } catch (CreditCardNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long id) {
        try {
            creditCardService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (CreditCardNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
