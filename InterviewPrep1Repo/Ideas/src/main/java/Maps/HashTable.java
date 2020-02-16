package Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
/*
    Implement Map and Entry interface, and override the methods to implement in interview.
    Implement with generic K, V . Make sure to implement getValue,setValue, getKey and equals methods from Entry Interface.
    HashTable will have Array of HashEntries. And each HashEntry will have link to the next HashEntry
 */
public class HashTable<K,V> implements Map<K,V> {
    public int size = 0;
    public static int SIZE = (int)Math.pow(2,4); //Default size initialized to 16. Once this size is reached will double the size and copy.
    HashEntry [] entries = new HashEntry[SIZE];
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public V get(Object key) {
        int indx = Math.abs(key.hashCode())%SIZE;
        if(this.entries[indx]==null){
            return null;
        }
        HashEntry<K,V> entry = this.entries[indx];
        while(entry.getKey()!=null && !entry.getKey().equals(key)){
            entry = entry.next;
        }
        if(entry.getKey() == null) return null;
        return (V)entry.getValue();
    }

    @Override
    public V put(K k, V v) {
        int idx = Math.abs(k.hashCode())%SIZE;
        if(this.entries[idx]!=null){
            HashEntry curr = this.entries[idx];
            HashEntry prev = null;
            while(curr != null ){
                prev = curr;
                curr = curr.next;
            }
            prev.next = new HashEntry(k,v);

        } else {
            this.entries[idx] = new HashEntry(k,v);
        }
        size++;
       return v;
    }

    @Override
    public V remove(Object o) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }
    @Override
        public int hashCode() {
            return this.hashCode();
        }
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }
    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public class HashEntry<K,V> implements Entry<K,V>{
        private K key;
        private V value;
        private HashEntry<K,V> next;
        public HashEntry(K k,V v){
            this.key = k;
            this.value = v;
        }
        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof HashEntry) {
                return ((HashEntry) o).key.equals(key) && ((HashEntry) o).value.equals(value);
            }
            return false;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V v) {
            return this.value = v;
        }
    }

    public static void main(String[] args) {
        Map<String,String> table = new HashTable<>();
        table.put("pet","dog");
        table.put("wild","lion");
        table.put("flower","rose");
        System.out.println(table.get("pet"));
        System.out.println(table.get("flower"));
    }
}
