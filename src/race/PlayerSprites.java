package race;

public class PlayerSprites {
	public static final int WIDTH  = 48,
							HEIGHT = 64;

	private static final int[][][] ANIMATIONS = {
			{{1, 0}},					// idle back
			{{1, 1}},                   // idle right
			{{1, 2}},                   // idle front
			{{1, 3}},                   // idle left
			{{0, 0}, {1, 0}, {2, 0}},   // walk back
			{{0, 1}, {1, 1}, {2, 1}},   // walk right
			{{0, 2}, {1, 2}, {2, 2}},   // walk forward
			{{0, 3}, {1, 3}, {2, 3}}    // walk left
	};

	private static final int[]
			DWARF_ELDER_OFFSET      = {0,  0},
			DWARF_GUARDIAN_OFFSET   = {3,  0},
			DWARF_HERO_OFFSET       = {6,  0},
			DWARF_LEADER_OFFSET     = {9,  0},
			DWARF_BLACKBEARD_OFFSET = {12, 0},
			DWARF_REDBEARD_OFFSET   = {15, 0};

	public static Sprite createElderSprite() {
		return new Sprite(WIDTH, HEIGHT, DWARF_ELDER_OFFSET[0], DWARF_ELDER_OFFSET[1], ANIMATIONS);
	}

	public static Sprite createGuardianSprite() {
		return new Sprite(WIDTH, HEIGHT, DWARF_GUARDIAN_OFFSET[0], DWARF_GUARDIAN_OFFSET[1], ANIMATIONS);
	}

	public static Sprite createHeroSprite() {
		return new Sprite(WIDTH, HEIGHT, DWARF_HERO_OFFSET[0], DWARF_HERO_OFFSET[1], ANIMATIONS);
	}

	public static Sprite createLeaderSprite() {
		return new Sprite(WIDTH, HEIGHT, DWARF_LEADER_OFFSET[0], DWARF_LEADER_OFFSET[1], ANIMATIONS);
	}

	public static Sprite createBlackBeardSprite() {
		return new Sprite(WIDTH, HEIGHT, DWARF_BLACKBEARD_OFFSET[0], DWARF_BLACKBEARD_OFFSET[1], ANIMATIONS);
	}

	public static Sprite createRedBeardSprite() {
		return new Sprite(WIDTH, HEIGHT, DWARF_REDBEARD_OFFSET[0], DWARF_REDBEARD_OFFSET[1], ANIMATIONS);
	}

	public static Sprite[] sprites() {
		return new Sprite[] { createElderSprite(),
							  createGuardianSprite(),
							  createHeroSprite(),
							  createLeaderSprite(),
							  createBlackBeardSprite(),
							  createRedBeardSprite() };
	}
}
