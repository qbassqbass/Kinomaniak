/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak_payments;

/**
 *
 * @author Qbass
 */
public class BitCoin implements PaymentStrategy {
    private String pubKey;
    private String privKey;
    
    public BitCoin(){
        
    }

    @Override
    public void pay(float amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
