package org.example.exmod;

import com.github.puzzle.core.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

    public static final String MOD_ID = "example-mod";
    public static final Identifier MOD_NAME = new Identifier(MOD_ID, "Example Mod");
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

}
