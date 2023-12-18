package htw.berlin.webtech;

import htw.berlin.webtech.Exceptions.CreditCardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public List<CreditCard> findAll() {
        Iterable<CreditCard> iterable = creditCardRepository.findAll();
        List<CreditCard> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    public CreditCard save(CreditCard creditCard) {
        // Hier können Sie Ihre Logik für die Kreditkartenverarbeitung hinzufügen
        return creditCardRepository.save(creditCard);
    }

    public CreditCard get(Long id) {
        Optional<CreditCard> creditCard = creditCardRepository.findById(id);
        if (creditCard.isPresent()) {
            return creditCard.get();
        } else {
            throw new CreditCardNotFoundException("CreditCard with id " + id + " not found");
        }
    }

    public void deleteById(Long id) {
        creditCardRepository.deleteById(id);
    }
}
