package com.sergei.recipe.converters;

import com.sergei.recipe.commands.NotesCommand;
import com.sergei.recipe.domain.Notes;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class Notes_To_NotesCommand implements Converter<Notes, NotesCommand> {
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if(notes==null) {
            return null;
        }
        final NotesCommand notesCommand=new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setNotes(notes.getNotes());

        return notesCommand;
    }
}
