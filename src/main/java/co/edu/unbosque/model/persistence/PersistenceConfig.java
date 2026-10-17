package co.edu.unbosque.model.persistence;

import java.util.Map;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/** Creates the JPA factory using the database settings from the process environment. */
public final class PersistenceConfig {

	private PersistenceConfig() {
	}

	public static EntityManagerFactory createEntityManagerFactory(String persistenceUnit) {
		Map<String, Object> properties = Map.of(
				"jakarta.persistence.jdbc.url", requiredEnvironmentVariable("PAPELERIA_DB_URL"),
				"jakarta.persistence.jdbc.user", requiredEnvironmentVariable("PAPELERIA_DB_USER"),
				"jakarta.persistence.jdbc.password", requiredEnvironmentVariable("PAPELERIA_DB_PASSWORD"));
		return Persistence.createEntityManagerFactory(persistenceUnit, properties);
	}

	private static String requiredEnvironmentVariable(String name) {
		String value = System.getenv(name);
		if (value == null || value.isBlank()) {
			throw new IllegalStateException("Define la variable de entorno " + name + " para conectar con MySQL.");
		}
		return value;
	}
}
