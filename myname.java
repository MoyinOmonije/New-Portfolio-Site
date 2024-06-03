import java.util.concurrent.Callable;

interface Reptile {
    ReptileEgg lay();
}

class FireDragon implements Reptile {
    @Override
    public ReptileEgg lay() {
        return new ReptileEgg(() -> new FireDragon());
    }

    public static void main(String[] args) throws Exception {
        FireDragon fireDragon = new FireDragon();
        System.out.println(fireDragon instanceof Reptile);
    }
}

class ReptileEgg {
    private boolean hatched = false;
    private Callable<Reptile> createReptile;

    public ReptileEgg(Callable<Reptile> createReptile) {
        this.createReptile = createReptile;
    }

    public Reptile hatch() throws Exception {
        if (hatched) {
            throw new IllegalStateException("Reptile egg cannot hatch more than once.");
        }
        hatched = true;
        return createReptile.call();
    }
}
