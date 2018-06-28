package jos.service.command.util;

public class BotMessages {
    private BotMessages() {
    }

    public static final String USER_WELCOME_REPLY = "Hello %s! To help you get started, send me 'start'. :)";
    public static final String INVALID_COMMAND_ARGUMENTS_REPLY = "You didn't provide valid arguments for your command. Write 'help' to see how to use the available commands!'";
    public static final String NO_API_KEY_PROVIDED_REPLY = "You didn't provide an api-key yet. To do this, send me 'register (api-key)'.";
    public static final String NO_PROJECT_ID_PROVIDED_REPLY = "You didn't provide an project-id yet. To do this, send me 'project (name)'.";
    public static final String NO_SERVICE_ID_PROVIDED_REPLY = "You didn't provide an service-id yet. To do this, send me 'service (name)'.";
    public static final String HELP_REPLY = "Help reply";
    public static final String COMMAND_FAILED_REPLY = "Failed to execute your command :(";
    public static final String INVALID_API_KEY_REPLY = "Your provided api-key was invalid";
    public static final String INVALID_COMMAND_REPLY = "You didn't provide a valid command. To see a list of available commands, send me 'help'.";
    public static final String INVALID_CHARACTERS_REPLY = "Sorry, your message contained invalid characters!";
    public static final String GENERAL_ERROR_REPLY = "Oops, an unknown error occured. :(";
    public static final String SUCCESSFULL_SHORTCUT_REPLY = "Successfully set a new shortcut!";
    public static final String INVALID_CLIENT_REPLY = "The bot you're trying to use for your mite entries is not verified :(";
    public static final String API_KEY_SET_SUCCESSFULLY_REPLY = "Your api-key has been set successfully!";

    public static final String F_SUCCESSFUL_ENTRY_REPLY = "A new entry with the duration %s minutes and the comment '%s' was created!";
    public static final String F_SICCESSFUL_SET_PROJECT_REPLY = "I set '%s' as your default project!";
    public static final String F_SICCESSFUL_SET_SERVICE_REPLY = "I set '%s' as your default service!";
}
