/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak_objs;

/**
 *
 * @author Qbass
 */



public class GoldCard {
    private int lastId;
    private GoldCard instance = null;
    
    private class GCImpl implements GC{    
        private final int id;
        private final int ownerId;
        private final float discount;

        @Override
        public int getId() {
            return id;
        }

        @Override
        public int getOwnerId() {
            return ownerId;
        }

        @Override
        public float getDiscount() {
            return discount;
        }

        public GCImpl(int id, int own, float disc){
            this.id = id;
            this.ownerId = own;
            this.discount = disc;
        }  
    }
    
    private GoldCard(){
        //get the last Card ID from DataBase
    }
    
    private int getLastId(){
        return this.lastId;
    }
    
    public GoldCard getInstance(){
        return (this.instance == null) ? (instance = new GoldCard()) : instance;
    }
    
    public GC createNewGC(int owner, float discount){        
        return (GC)new GCImpl(this.getLastId(), owner, discount);
    }
    
}
