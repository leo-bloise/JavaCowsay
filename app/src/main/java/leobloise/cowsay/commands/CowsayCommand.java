package leobloise.cowsay.commands;

import leobloise.cowsay.Animal;
import leobloise.cowsay.Message;
import leobloise.cowsay.utils.AnimalLoader;
import leobloise.cowsay.utils.CowsayBuilder;
import leobloise.parser.Command;

public class CowsayCommand implements Command {
    private Message message;
    private Animal animal;
    private CowsayBuilder builder = new CowsayBuilder(
            new AnimalLoader()
    );
    public CowsayCommand(Message message, Animal animal) {
        this.message = message;
        this.animal = animal;
    }
    @Override
    public void execute() {
        String cowsay = builder.buildBubble(message.get())
                .buildAnimal(animal.get())
                .build();
        System.out.println(cowsay);
    }
}
