package ru.tinkoff.edu.java.bot.component.handler;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.component.command.UntrackCommand;
import ru.tinkoff.edu.java.bot.exception.UnregisteredUserException;
import ru.tinkoff.edu.java.bot.�lient.IScrapperClient;
import ru.tinkoff.edu.java.bot.�lient.request.RemoveLinkRequest;

import java.net.URI;

@Component
public record UntrackHandlerImpl(
        IScrapperClient scrapperClient,
        UntrackCommand handledCommand
) implements IHandler<UntrackCommand> {

    private static final String BOT_REPLY = "��� �� �����������?";
    private static final String BOT_REPLY_SUCCESS = "������! ������ ������ �� �������������";
    private static final String BOT_REPLY_INCORRECT_LINK = String.format(
            "�������, ������ ���������.\n���������� ��� ���.\n%s",
            BOT_REPLY);
    private static final String BOT_REPLY_UNKNOWN_ERROR = String.format(
            "���-�� ����� �� ���.\n���������� ��� ���.\n%s",
            BOT_REPLY);
    private static final String BOT_REPLY_UNREGISTERED = "� ��� ������-�� �� ����. ����������, ��������� /start";

    @Override
    public SendMessage handle(Update update) {
        String command = update.message().text();
        Long id = update.message().chat().id();
        String reply;
        if ((reply = this.reply(update)) != null
                && reply.endsWith(BOT_REPLY)
        ) {
            // TODO: check reply
            try {
                URI url = URI.create(command);
                this.scrapperClient.untrack(id, new RemoveLinkRequest(url));
                return new SendMessage(id, BOT_REPLY_SUCCESS);
            } catch (IllegalArgumentException e) {
                return new SendMessage(id, BOT_REPLY_INCORRECT_LINK).replyMarkup(new ForceReply());
            } catch (UnregisteredUserException e) {
                return new SendMessage(id, BOT_REPLY_UNREGISTERED);
            } catch (Exception e) {
                return new SendMessage(id, BOT_REPLY_UNKNOWN_ERROR).replyMarkup(new ForceReply());
            }
        } else if (command.equals(handledCommand.command())) {
            return new SendMessage(id, BOT_REPLY).replyMarkup(new ForceReply());
        }
        return null;
    }

    private String reply(Update update) {
        return update.message().replyToMessage() == null
                ? null
                : update.message().replyToMessage().text();
    }

    @Override
    public UntrackCommand handledCommand() {
        return this.handledCommand;
    }
}
