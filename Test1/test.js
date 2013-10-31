/**
 * Transpose chord
 * @param chord String
 * @param amount Integer
 * @returns
 */
function transposeChord(chord, amount) {
	if (typeof chord == 'undefined') {
		return null;
	}
	if (chord == "") {
		return null;
	}
	
	/**
	 * FORMAT TEXT: ABC/DEF ==> Abc/Def
	 */
	chord = chord.toLowerCase();
	// The first letter
	chord = chord.replace(/^./, function(char) {
		return char.toUpperCase();
	});
	// The letter after "/" character
	chord = chord.replace(/\/./, function(char) {
		return char.toUpperCase();
	});
	/**
	 * END FORMAT TEXT
	 */
	
	var sameScale = [ "Db", "C#", "Eb", "D#", "Gb", "F#", "Ab", "G#", "Bb", "A#" ];
	var scale = [ "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" ];
	chord = chord.replace(/[DEGAB]b/, function(match) {
		return sameScale[(sameScale.indexOf(match) + 1)];
	});
	return chord.replace(/[CDEFGAB]#?/g, function(match) {
		var i = (scale.indexOf(match) + amount) % scale.length;
		return scale[i < 0 ? i + scale.length : i];
	});
}