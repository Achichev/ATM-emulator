package atm_emulator.command;

import atm_emulator.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
