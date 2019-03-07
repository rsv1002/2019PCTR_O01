package O01;

public class HiloMovimiento implements Runnable {

        private Ball bola;
        private Board board;

        public HiloMovimiento(Ball bola, Board board) {

            this.bola=bola;
            this.board=board;

        }

        @Override
        public void run() {

            try {

                while (true) {

                    bola.move();
                    board.repaint();
                    Thread.sleep(30);

                }

            } catch (InterruptedException e){

                return;

            }

        }

}

