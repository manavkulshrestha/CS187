class Note:
    letter = None
    octave = None

    def __init__(self, letter, octave):
        self.letter = letter
        self.octave = octave
    def __repr__(self):
        return f'n{self.letter}{self.octave}'
    def get_num(self):
        return note_to_num_dict[f'{self.letter}{self.octave}']
    def equals(self, that):
        if that.letter is self.letter and that.octave is self.octave:
            return True
        return False
    def equals_ignore_octave(that):
        if that.letter is self.letter:
            return True
        return False
    def get_rawletter():
        return self.letter[0]

class Node:
    parent_index = None
    note = None
    children_index = None

    def __init__(self, note, children_index=None, parent_index=None):
        self.parent_index = parent_index
        self.note = note
        self.children_index = children_index
    def __repr__(self):
        return ''.join(['{',str(self.parent_index),',',str(self.note),',',str(self.children_index),'}'])

