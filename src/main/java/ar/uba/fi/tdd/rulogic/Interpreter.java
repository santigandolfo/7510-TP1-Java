package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDatabaseException;
import ar.uba.fi.tdd.rulogic.exceptions.InvalidQueryException;

import java.util.ArrayList;
import java.util.List;

/**
 * Console application.
 *
 */
public class Interpreter {
	private Database mParsedDatabase;

	public Interpreter(ArrayList<String> database) throws InvalidDatabaseException {
		mParsedDatabase = new Database();
		mParsedDatabase.generateParsedDatabase(database);
	}

	public boolean checkQuery(String params) throws InvalidQueryException {
		Element query = new Element(params);
		if (!new Verificator().isQuery(params)) {
			throw new InvalidQueryException();
		}
		return mParsedDatabase.queryElementsAreInDatabase(query);
	}

}
