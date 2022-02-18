package idea.verlif.socket.commad.keypress;

import idea.verlif.socket.command.SocketCommand;
import idea.verlif.socket.core.server.holder.ClientHolder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/2/18 9":50
 */
public class KeyPressCommand implements SocketCommand<KeyPressConfig> {

    private final Robot robot;
    private KeyPressConfig config;

    public KeyPressCommand() throws AWTException {
        robot = new Robot();
    }

    @Override
    public String[] keys() {
        return new String[]{"press"};
    }

    @Override
    public void onLoad(KeyPressConfig keyPressConfig) {
        this.config = keyPressConfig;
    }

    @Override
    public void run(ClientHolder.ClientHandler clientHandler, String s) {
        String[] sp = s.split(" ");
        List<Integer> list = new ArrayList<>();
        try {
            for (int i = 0, size = (int) Math.min(sp.length, config.getMax()); i < size; i++) {
                Thread.sleep(config.getInterval());
                int code = parse(sp[i]);
                if (code >= 0) {
                    robot.keyPress(code);
                    list.add(list.size(), code);
                } else {
                    clientHandler.sendMessage("Unknown input - " + sp[i] + ". Maybe you should use [space] to split it.");
                    clientHandler.sendMessage("Key press is over");
                    break;
                }
            }
        } catch (Exception e) {
            clientHandler.sendMessage("Something wrong! " + e.getMessage());
        }
        for (Integer integer : list) {
            robot.keyRelease(integer);
        }
    }

    /**
     * 解析字符串到KeyEvent的KeyCode
     *
     * @param s 原字符串
     * @return KeyEvent对应的KeyCode，若不存在则返回-1
     */
    private int parse(String s) {
        if (s.length() == 0) {
            return -1;
        }
        String key = s.toUpperCase(Locale.ROOT);
        if (s.length() == 1) {
            int c = key.charAt(0);
            if (c >= KeyEvent.VK_0 && c <= KeyEvent.VK_9 ||
                    c >= KeyEvent.VK_A && c <= KeyEvent.VK_Z) {
                return c;
            }
        }

        switch (key) {
            case "NUMBER_0":
            case "N0":
                return KeyEvent.VK_NUMPAD0;
            case "NUMBER_1":
            case "N1":
                return KeyEvent.VK_NUMPAD1;
            case "NUMBER_2":
            case "N2":
                return KeyEvent.VK_NUMPAD2;
            case "NUMBER_3":
            case "N3":
                return KeyEvent.VK_NUMPAD3;
            case "NUMBER_4":
            case "N4":
                return KeyEvent.VK_NUMPAD4;
            case "NUMBER_5":
            case "N5":
                return KeyEvent.VK_NUMPAD5;
            case "NUMBER_6":
            case "N6":
                return KeyEvent.VK_NUMPAD6;
            case "NUMBER_7":
            case "N7":
                return KeyEvent.VK_NUMPAD7;
            case "NUMBER_8":
            case "N8":
                return KeyEvent.VK_NUMPAD8;
            case "NUMBER_9":
            case "N9":
                return KeyEvent.VK_NUMPAD9;

            case "ENTER":
                return KeyEvent.VK_ENTER;
            case "BACK":
            case "BACKSPACE":
            case "BACK_SPACE":
                return KeyEvent.VK_BACK_SPACE;
            case "TAB":
                return KeyEvent.VK_TAB;
            case "CANCEL":
                return KeyEvent.VK_CANCEL;
            case "CLEAR":
                return KeyEvent.VK_CLEAR;
            case "COMPOSE":
                return KeyEvent.VK_COMPOSE;
            case "PAUSE":
                return KeyEvent.VK_PAUSE;
            case "CAPSLK":
            case "CAPSLOCK":
            case "CAPS_LOCK":
                return KeyEvent.VK_CAPS_LOCK;
            case "ESC":
            case "ESCAPE":
                return KeyEvent.VK_ESCAPE;
            case "SPACE":
                return KeyEvent.VK_SPACE;
            case "PAGE_UP":
                return KeyEvent.VK_PAGE_UP;
            case "PAGE_DOWN":
                return KeyEvent.VK_PAGE_DOWN;
            case "END":
                return KeyEvent.VK_END;
            case "HOME":
                return KeyEvent.VK_HOME;
            case "LEFT":
                return KeyEvent.VK_LEFT;
            case "UP":
                return KeyEvent.VK_UP;
            case "RIGHT":
                return KeyEvent.VK_RIGHT;
            case "DOWN":
                return KeyEvent.VK_DOWN;
            case "BEGIN":
                return KeyEvent.VK_BEGIN;

            // modifiers
            case "SHIFT":
                return KeyEvent.VK_SHIFT;
            case "CONTROL":
                return KeyEvent.VK_CONTROL;
            case "ALT":
                return KeyEvent.VK_ALT;
            case "META":
                return KeyEvent.VK_META;
            case "ALT_GRAPH":
                return KeyEvent.VK_ALT_GRAPH;

            // punctuation
            case ",":
            case "COMMA":
                return KeyEvent.VK_COMMA;
            case "DOT":
            case ".":
            case "PERIOD":
                return KeyEvent.VK_PERIOD;
            case "/":
            case "SLASH":
                return KeyEvent.VK_SLASH;
            case ";":
            case "SEMICOLON":
                return KeyEvent.VK_SEMICOLON;
            case "=":
            case "EQUALS":
                return KeyEvent.VK_EQUALS;
            case "[":
            case "OPEN_BRACKET":
                return KeyEvent.VK_OPEN_BRACKET;
            case "\\":
            case "BACK_SLASH":
                return KeyEvent.VK_BACK_SLASH;
            case "]":
            case "CLOSE_BRACKET":
                return KeyEvent.VK_CLOSE_BRACKET;

            // numpad numeric keys handled below
            case "MULTIPLY":
                return KeyEvent.VK_MULTIPLY;
            case "ADD":
                return KeyEvent.VK_ADD;
            case "SEPARATOR":
                return KeyEvent.VK_SEPARATOR;
            case "-":
            case "SUBTRACT":
                return KeyEvent.VK_SUBTRACT;
            case "DECIMAL":
                return KeyEvent.VK_DECIMAL;
            case "DIVIDE":
                return KeyEvent.VK_DIVIDE;
            case "DELETE":
                return KeyEvent.VK_DELETE;
            case "NUMLOCK":
            case "NUM_LOCK":
                return KeyEvent.VK_NUM_LOCK;
            case "SCROLLLOCK":
            case "SCROLL_LOCK":
                return KeyEvent.VK_SCROLL_LOCK;

            case "WIN":
            case "WINDOWS":
                return KeyEvent.VK_WINDOWS;
            case "CONTEXT_MENU":
                return KeyEvent.VK_CONTEXT_MENU;

            case "F1":
                return KeyEvent.VK_F1;
            case "F2":
                return KeyEvent.VK_F2;
            case "F3":
                return KeyEvent.VK_F3;
            case "F4":
                return KeyEvent.VK_F4;
            case "F5":
                return KeyEvent.VK_F5;
            case "F6":
                return KeyEvent.VK_F6;
            case "F7":
                return KeyEvent.VK_F7;
            case "F8":
                return KeyEvent.VK_F8;
            case "F9":
                return KeyEvent.VK_F9;
            case "F10":
                return KeyEvent.VK_F10;
            case "F11":
                return KeyEvent.VK_F11;
            case "F12":
                return KeyEvent.VK_F12;
            case "F13":
                return KeyEvent.VK_F13;
            case "F14":
                return KeyEvent.VK_F14;
            case "F15":
                return KeyEvent.VK_F15;
            case "F16":
                return KeyEvent.VK_F16;
            case "F17":
                return KeyEvent.VK_F17;
            case "F18":
                return KeyEvent.VK_F18;
            case "F19":
                return KeyEvent.VK_F19;
            case "F20":
                return KeyEvent.VK_F20;
            case "F21":
                return KeyEvent.VK_F21;
            case "F22":
                return KeyEvent.VK_F22;
            case "F23":
                return KeyEvent.VK_F23;
            case "F24":
                return KeyEvent.VK_F24;

            case "PS":
            case "PRTSC":
            case "PRINTSCREEN":
            case "PRINT_SCREEN":
                return KeyEvent.VK_PRINTSCREEN;
            case "INSERT":
                return KeyEvent.VK_INSERT;
            case "HELP":
                return KeyEvent.VK_HELP;
            case "`":
            case "BACK_QUOTE":
                return KeyEvent.VK_BACK_QUOTE;
            case "'":
            case "QUOTE":
                return KeyEvent.VK_QUOTE;

            case "KP_UP":
                return KeyEvent.VK_KP_UP;
            case "KP_DOWN":
                return KeyEvent.VK_KP_DOWN;
            case "KP_LEFT":
                return KeyEvent.VK_KP_LEFT;
            case "KP_RIGHT":
                return KeyEvent.VK_KP_RIGHT;

            default:
                return -1;
        }
    }

}
