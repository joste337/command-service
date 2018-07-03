package jos.service.command.annotations;


public @interface DiscordCommand {
    String value();

    String helpText() default "No helptext defined";
}