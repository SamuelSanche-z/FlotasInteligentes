package FlotasInteligentes.Domain;

public class Conductores {
        private int ID;
        private String name;
        private String email;
        private int idFlotas;


        // Constructor completo
        public Conductores(int ID, String name, String email, int idFlotas) {
            this.ID = ID;
            this.name = name;
            this.email = email;
            this.idFlotas = idFlotas;
        }

        // Constructor vacío
        public Conductores() {
        }

        // Getters y Setters
        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getIdFlotas() {
            return idFlotas;
        }

        public void setIdFlotas(int idFlotas) {
            this.idFlotas = idFlotas;
        }
}
