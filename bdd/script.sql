-- Schéma PostgreSQL de démonstration ShopWise (données inventées uniquement)
CREATE TABLE IF NOT EXISTS shop (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(120) NOT NULL
);
CREATE TABLE IF NOT EXISTS customer (
  id BIGSERIAL PRIMARY KEY,
  shop_id BIGINT NOT NULL REFERENCES shop(id),
  name VARCHAR(160) NOT NULL,
  email VARCHAR(254) NOT NULL,
  phone VARCHAR(40),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT uq_customer_email_per_shop UNIQUE (shop_id, email)
);
CREATE TABLE IF NOT EXISTS appointment (
  id BIGSERIAL PRIMARY KEY,
  customer_id BIGINT NOT NULL REFERENCES customer(id),
  appointment_date DATE NOT NULL,
  service VARCHAR(160) NOT NULL,
  status VARCHAR(16) NOT NULL DEFAULT 'PLANNED'
    CHECK (status IN ('PLANNED','HONORED','CANCELLED')),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS loyalty_transaction (
  id BIGSERIAL PRIMARY KEY,
  customer_id BIGINT NOT NULL REFERENCES customer(id),
  appointment_id BIGINT UNIQUE REFERENCES appointment(id),
  points_delta INTEGER NOT NULL CHECK (points_delta <> 0),
  reason VARCHAR(200) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX IF NOT EXISTS ix_appointment_date_status ON appointment(appointment_date, status);
CREATE INDEX IF NOT EXISTS ix_appointment_customer ON appointment(customer_id);
CREATE INDEX IF NOT EXISTS ix_loyalty_customer_date ON loyalty_transaction(customer_id, created_at);

INSERT INTO shop(id,name) VALUES (1,'Épicerie de démonstration') ON CONFLICT (id) DO NOTHING;
INSERT INTO customer(id,shop_id,name,email,phone) VALUES
 (1,1,'Camille Exemple','camille@example.test','0600000001'),
 (2,1,'Alex Démonstration','alex@example.test','0600000002')
ON CONFLICT (id) DO NOTHING;
INSERT INTO appointment(id,customer_id,appointment_date,service,status) VALUES
 (1,1,CURRENT_DATE + 1,'Conseil produits','PLANNED'),
 (2,2,CURRENT_DATE - 1,'Retrait de commande','HONORED')
ON CONFLICT (id) DO NOTHING;
INSERT INTO loyalty_transaction(customer_id,appointment_id,points_delta,reason)
VALUES (2,2,10,'Rendez-vous honoré') ON CONFLICT (appointment_id) DO NOTHING;
