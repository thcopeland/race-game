package race.level;

public enum MapTile implements Tiled {
	/*
	 * The next few hundred lines contain mostly data describing the 200-some
	 * map tiles that the games currently supports.
	 */

	DIRT_EXTRA_1		(0, 0, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_IN_NW			(1, 0, TerrainLayout.IN_NW,  TerrainLayout.GRASS_DIRT),
	DIRT_IN_NE			(2, 0, TerrainLayout.IN_NE,  TerrainLayout.GRASS_DIRT),
	DIRT_EXTRA_2		(0, 1, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_IN_SW			(1, 1, TerrainLayout.IN_SW,  TerrainLayout.GRASS_DIRT),
	DIRT_IN_SE			(2, 1, TerrainLayout.IN_SE,  TerrainLayout.GRASS_DIRT),
	DIRT_OUT_NW			(0, 2, TerrainLayout.OUT_NW, TerrainLayout.GRASS_DIRT),
	DIRT_OUT_N			(1, 2, TerrainLayout.OUT_N,  TerrainLayout.GRASS_DIRT),
	DIRT_OUT_NE			(2, 2, TerrainLayout.OUT_NE, TerrainLayout.GRASS_DIRT),
	DIRT_OUT_W			(0, 3, TerrainLayout.OUT_W,  TerrainLayout.GRASS_DIRT),
	DIRT_OUT			(1, 3, TerrainLayout.FLAT,   TerrainLayout.GRASS),
	DIRT_OUT_E			(2, 3, TerrainLayout.OUT_E,  TerrainLayout.GRASS_DIRT),
	DIRT_OUT_SW			(0, 4, TerrainLayout.OUT_SW, TerrainLayout.GRASS_DIRT),
	DIRT_OUT_S			(1, 4, TerrainLayout.OUT_S,  TerrainLayout.GRASS_DIRT),
	DIRT_OUT_SE			(2, 4, TerrainLayout.OUT_SE, TerrainLayout.GRASS_DIRT),
	DIRT_1				(0, 5, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_2				(1, 5, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_3				(2, 5, TerrainLayout.FLAT,   TerrainLayout.DIRT),

	GRASS_EXTRA_1       (3, 0, TerrainLayout.FLAT,   TerrainLayout.GRASS),
	GRASS_IN_NW         (4, 0, TerrainLayout.IN_NW,  TerrainLayout.GRASS_DIRT),
	GRASS_IN_NE         (5, 0, TerrainLayout.IN_NE,  TerrainLayout.GRASS_DIRT),
	GRASS_EXTRA_2       (3, 1, TerrainLayout.FLAT,   TerrainLayout.GRASS),
	GRASS_IN_SW         (4, 1, TerrainLayout.IN_SW,  TerrainLayout.GRASS_DIRT),
	GRASS_IN_SE         (5, 1, TerrainLayout.IN_SE,  TerrainLayout.GRASS_DIRT),
	GRASS_OUT_NW        (3, 2, TerrainLayout.OUT_NW, TerrainLayout.GRASS_DIRT),
	GRASS_OUT_N         (4, 2, TerrainLayout.OUT_N,  TerrainLayout.GRASS_DIRT),
	GRASS_OUT_NE        (5, 2, TerrainLayout.OUT_NE, TerrainLayout.GRASS_DIRT),
	GRASS_OUT_W         (3, 3, TerrainLayout.OUT_W,  TerrainLayout.GRASS_DIRT),
	GRASS_OUT           (4, 3, TerrainLayout.FLAT,   TerrainLayout.GRASS),
	GRASS_OUT_E         (5, 3, TerrainLayout.OUT_E,  TerrainLayout.GRASS_DIRT),
	GRASS_OUT_SW        (3, 4, TerrainLayout.OUT_SW, TerrainLayout.GRASS_DIRT),
	GRASS_OUT_S         (4, 4, TerrainLayout.OUT_S,  TerrainLayout.GRASS_DIRT),
	GRASS_OUT_SE        (5, 4, TerrainLayout.OUT_SE, TerrainLayout.GRASS_DIRT),
	GRASS_1             (3, 5, TerrainLayout.FLAT,   TerrainLayout.GRASS),
	GRASS_2             (4, 5, TerrainLayout.FLAT,   TerrainLayout.GRASS),
	GRASS_3             (5, 5, TerrainLayout.FLAT,   TerrainLayout.GRASS),

	WATER_EXTRA_1       (6, 0, TerrainLayout.FLAT,   TerrainLayout.WATER),
	WATER_IN_NW         (7, 0, TerrainLayout.IN_NW,  TerrainLayout.WATER_GRASS),
	WATER_IN_NE         (8, 0, TerrainLayout.IN_NE,  TerrainLayout.WATER_GRASS),
	WATER_EXTRA_2       (6, 1, TerrainLayout.FLAT,   TerrainLayout.WATER),
	WATER_IN_SW         (7, 1, TerrainLayout.IN_SW,  TerrainLayout.WATER_GRASS),
	WATER_IN_SE         (8, 1, TerrainLayout.IN_SE,  TerrainLayout.WATER_GRASS),
	WATER_OUT_NW        (6, 2, TerrainLayout.OUT_NW, TerrainLayout.WATER_GRASS),
	WATER_OUT_N         (7, 2, TerrainLayout.OUT_N,  TerrainLayout.WATER_GRASS),
	WATER_OUT_NE        (8, 2, TerrainLayout.OUT_NE, TerrainLayout.WATER_GRASS),
	WATER_OUT_W         (6, 3, TerrainLayout.OUT_W,  TerrainLayout.WATER_GRASS),
	WATER_OUT           (7, 3, TerrainLayout.FLAT,   TerrainLayout.WATER),
	WATER_OUT_E         (8, 3, TerrainLayout.OUT_E,  TerrainLayout.WATER_GRASS),
	WATER_OUT_SW        (6, 4, TerrainLayout.OUT_SW, TerrainLayout.WATER_GRASS),
	WATER_OUT_S         (7, 4, TerrainLayout.OUT_S,  TerrainLayout.WATER_GRASS),
	WATER_OUT_SE        (8, 4, TerrainLayout.OUT_SE, TerrainLayout.WATER_GRASS),
	WATER_1             (6, 5, TerrainLayout.FLAT,   TerrainLayout.WATER),
	WATER_2             (7, 5, TerrainLayout.FLAT,   TerrainLayout.WATER),
	WATER_3             (8, 5, TerrainLayout.FLAT,   TerrainLayout.WATER),

	BRACKISH_EXTRA_1    (9,  0, TerrainLayout.FLAT,   TerrainLayout.BRACKISH),
	BRACKISH_IN_NW      (10, 0, TerrainLayout.IN_NW,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_IN_NE      (11, 0, TerrainLayout.IN_NE,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_EXTRA_2    (9,  1, TerrainLayout.FLAT,   TerrainLayout.BRACKISH),
	BRACKISH_IN_SW      (10, 1, TerrainLayout.IN_SW,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_IN_SE      (11, 1, TerrainLayout.IN_SE,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT_NW     (9,  2, TerrainLayout.OUT_NW, TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT_N      (10, 2, TerrainLayout.OUT_N,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT_NE     (11, 2, TerrainLayout.OUT_NE, TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT_W      (9,  3, TerrainLayout.OUT_W,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT        (10, 3, TerrainLayout.FLAT,   TerrainLayout.BRACKISH),
	BRACKISH_OUT_E      (11, 3, TerrainLayout.OUT_E,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT_SW     (9,  4, TerrainLayout.OUT_SW, TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT_S      (10, 4, TerrainLayout.OUT_S,  TerrainLayout.BRACKISH_GRASS),
	BRACKISH_OUT_SE     (11, 4, TerrainLayout.OUT_SE, TerrainLayout.BRACKISH_GRASS),
	BRACKISH_1          (9,  5, TerrainLayout.FLAT,   TerrainLayout.BRACKISH),
	BRACKISH_2          (10, 5, TerrainLayout.FLAT,   TerrainLayout.BRACKISH),
	BRACKISH_3          (11, 5, TerrainLayout.FLAT,   TerrainLayout.BRACKISH),

	DIRT_WATER_EXTRA_1  (12, 0, TerrainLayout.FLAT,   TerrainLayout.WATER),
	DIRT_WATER_IN_NW    (13, 0, TerrainLayout.IN_NW,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_IN_NE    (14, 0, TerrainLayout.IN_NE,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_EXTRA_2  (12, 1, TerrainLayout.FLAT,   TerrainLayout.WATER),
	DIRT_WATER_IN_SW    (13, 1, TerrainLayout.IN_SW,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_IN_SE    (14, 1, TerrainLayout.IN_SE,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT_NW   (12, 2, TerrainLayout.OUT_NW, TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT_N    (13, 2, TerrainLayout.OUT_N,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT_NE   (14, 2, TerrainLayout.OUT_NE, TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT_W    (12, 3, TerrainLayout.OUT_W,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT      (13, 3, TerrainLayout.FLAT,   TerrainLayout.WATER),
	DIRT_WATER_OUT_E    (14, 3, TerrainLayout.OUT_E,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT_SW   (12, 4, TerrainLayout.OUT_SW, TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT_S    (13, 4, TerrainLayout.OUT_S,  TerrainLayout.WATER_DIRT),
	DIRT_WATER_OUT_SE   (14, 4, TerrainLayout.OUT_SE, TerrainLayout.WATER_DIRT),
	DIRT_WATER_1        (12, 5, TerrainLayout.FLAT,   TerrainLayout.WATER),
	DIRT_WATER_2        (13, 5, TerrainLayout.FLAT,   TerrainLayout.WATER),
	DIRT_WATER_3        (14, 5, TerrainLayout.FLAT,   TerrainLayout.WATER),

	LAVA_EXTRA_1        (15, 0, TerrainLayout.FLAT,   TerrainLayout.LAVA),
	LAVA_IN_NW          (16, 0, TerrainLayout.IN_NW,  TerrainLayout.LAVA_ROCK),
	LAVA_IN_NE          (17, 0, TerrainLayout.IN_NE,  TerrainLayout.LAVA_ROCK),
	LAVA_EXTRA_2        (15, 1, TerrainLayout.FLAT,   TerrainLayout.LAVA),
	LAVA_IN_SW          (16, 1, TerrainLayout.IN_SW,  TerrainLayout.LAVA_ROCK),
	LAVA_IN_SE          (17, 1, TerrainLayout.IN_SE,  TerrainLayout.LAVA_ROCK),
	LAVA_OUT_NW         (15, 2, TerrainLayout.OUT_NW, TerrainLayout.LAVA_ROCK),
	LAVA_OUT_N          (16, 2, TerrainLayout.OUT_N,  TerrainLayout.LAVA_ROCK),
	LAVA_OUT_NE         (17, 2, TerrainLayout.OUT_NE, TerrainLayout.LAVA_ROCK),
	LAVA_OUT_W          (15, 3, TerrainLayout.OUT_W,  TerrainLayout.LAVA_ROCK),
	LAVA_OUT            (16, 3, TerrainLayout.FLAT,   TerrainLayout.LAVA),
	LAVA_OUT_E          (17, 3, TerrainLayout.OUT_E,  TerrainLayout.LAVA_ROCK),
	LAVA_OUT_SW         (15, 4, TerrainLayout.OUT_SW, TerrainLayout.LAVA_ROCK),
	LAVA_OUT_S          (16, 4, TerrainLayout.OUT_S,  TerrainLayout.LAVA_ROCK),
	LAVA_OUT_SE         (17, 4, TerrainLayout.OUT_SE, TerrainLayout.LAVA_ROCK),
	LAVA_1              (15, 5, TerrainLayout.FLAT,   TerrainLayout.LAVA),
	LAVA_2              (16, 5, TerrainLayout.FLAT,   TerrainLayout.LAVA),
	LAVA_3              (17, 5, TerrainLayout.FLAT,   TerrainLayout.LAVA),

	ROCK_EXTRA_1        (18, 0, TerrainLayout.FLAT,   TerrainLayout.LAVA),
	ROCK_IN_NW          (19, 0, TerrainLayout.IN_NW,  TerrainLayout.ROCK_LAVA),
	ROCK_IN_NE          (20, 0, TerrainLayout.IN_NE,  TerrainLayout.ROCK_LAVA),
	ROCK_EXTRA_2        (18, 1, TerrainLayout.FLAT,   TerrainLayout.LAVA),
	ROCK_IN_SW          (19, 1, TerrainLayout.IN_SW,  TerrainLayout.ROCK_LAVA),
	ROCK_IN_SE          (20, 1, TerrainLayout.IN_SE,  TerrainLayout.ROCK_LAVA),
	ROCK_OUT_NW         (18, 2, TerrainLayout.OUT_NW, TerrainLayout.ROCK_LAVA),
	ROCK_OUT_N          (19, 2, TerrainLayout.OUT_N,  TerrainLayout.ROCK_LAVA),
	ROCK_OUT_NE         (20, 2, TerrainLayout.OUT_NE, TerrainLayout.ROCK_LAVA),
	ROCK_OUT_W          (18, 3, TerrainLayout.OUT_W,  TerrainLayout.ROCK_LAVA),
	ROCK_OUT            (19, 3, TerrainLayout.FLAT,   TerrainLayout.ROCK),
	ROCK_OUT_E          (20, 3, TerrainLayout.OUT_E,  TerrainLayout.ROCK_LAVA),
	ROCK_OUT_SW         (18, 4, TerrainLayout.OUT_SW, TerrainLayout.ROCK_LAVA),
	ROCK_OUT_S          (19, 4, TerrainLayout.OUT_S,  TerrainLayout.ROCK_LAVA),
	ROCK_OUT_SE         (20, 4, TerrainLayout.OUT_SE, TerrainLayout.ROCK_LAVA),
	ROCK_1              (18, 5, TerrainLayout.FLAT,   TerrainLayout.ROCK),
	ROCK_2              (19, 5, TerrainLayout.FLAT,   TerrainLayout.ROCK),
	ROCK_3              (20, 5, TerrainLayout.FLAT,   TerrainLayout.ROCK),

	DIRT_ROCK_EXTRA_1   (21, 0, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_ROCK_IN_NW     (22, 0, TerrainLayout.IN_NW,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_IN_NE     (23, 0, TerrainLayout.IN_NE,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_EXTRA_2   (21, 1, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_ROCK_IN_SW     (22, 1, TerrainLayout.IN_SW,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_IN_SE     (23, 1, TerrainLayout.IN_SE,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT_NW    (21, 2, TerrainLayout.OUT_NW, TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT_N     (22, 2, TerrainLayout.OUT_N,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT_NE    (23, 2, TerrainLayout.OUT_NE, TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT_W     (21, 3, TerrainLayout.OUT_W,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT       (22, 3, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_ROCK_OUT_E     (23, 3, TerrainLayout.OUT_E,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT_SW    (21, 4, TerrainLayout.OUT_SW, TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT_S     (22, 4, TerrainLayout.OUT_S,  TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_OUT_SE    (23, 4, TerrainLayout.OUT_SE, TerrainLayout.DIRT_ROCK),
	DIRT_ROCK_1         (21, 5, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_ROCK_2         (22, 5, TerrainLayout.FLAT,   TerrainLayout.DIRT),
	DIRT_ROCK_3         (23, 5, TerrainLayout.FLAT,   TerrainLayout.DIRT),

	ROCK_HOLE_EXTRA_1   (24, 0, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	ROCK_HOLE_IN_NW     (25, 0, TerrainLayout.IN_NW,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_IN_NE     (26, 0, TerrainLayout.IN_NE,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_EXTRA_2   (24, 1, TerrainLayout.FLAT,   TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_IN_SW     (25, 1, TerrainLayout.IN_SW,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_IN_SE     (26, 1, TerrainLayout.IN_SE,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT_NW    (24, 2, TerrainLayout.OUT_NW, TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT_N     (25, 2, TerrainLayout.OUT_N,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT_NE    (26, 2, TerrainLayout.OUT_NE, TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT_W     (24, 3, TerrainLayout.OUT_W,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT       (25, 3, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	ROCK_HOLE_OUT_E     (26, 3, TerrainLayout.OUT_E,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT_SW    (24, 4, TerrainLayout.OUT_SW, TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT_S     (25, 4, TerrainLayout.OUT_S,  TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_OUT_SE    (26, 4, TerrainLayout.OUT_SE, TerrainLayout.HOLE_ROCK),
	ROCK_HOLE_1         (24, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	ROCK_HOLE_2         (25, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	ROCK_HOLE_3         (26, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),

	GRASS_HOLE_EXTRA_1  (27, 0, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	GRASS_HOLE_IN_NW    (28, 0, TerrainLayout.IN_NW,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_IN_NE    (29, 0, TerrainLayout.IN_NE,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_EXTRA_2  (27, 1, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	GRASS_HOLE_IN_SW    (28, 1, TerrainLayout.IN_SW,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_IN_SE    (29, 1, TerrainLayout.IN_SE,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT_NW   (27, 2, TerrainLayout.OUT_NW, TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT_N    (28, 2, TerrainLayout.OUT_N,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT_NE   (29, 2, TerrainLayout.OUT_NE, TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT_W    (27, 3, TerrainLayout.OUT_W,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT      (28, 3, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	GRASS_HOLE_OUT_E    (29, 3, TerrainLayout.OUT_E,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT_SW   (27, 4, TerrainLayout.OUT_SW, TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT_S    (28, 4, TerrainLayout.OUT_S,  TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_OUT_SE   (29, 4, TerrainLayout.OUT_SE, TerrainLayout.HOLE_GRASS),
	GRASS_HOLE_1        (27, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	GRASS_HOLE_2        (28, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	GRASS_HOLE_3        (29, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),

	DIRT_HOLE_EXTRA_1   (30, 0, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	DIRT_HOLE_IN_NW     (31, 0, TerrainLayout.IN_NW,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_IN_NE     (32, 0, TerrainLayout.IN_NE,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_EXTRA_2   (30, 1, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	DIRT_HOLE_IN_SW     (31, 1, TerrainLayout.IN_SW,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_IN_SE     (32, 1, TerrainLayout.IN_SE,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT_NW    (30, 2, TerrainLayout.OUT_NW, TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT_N     (31, 2, TerrainLayout.OUT_N,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT_NE    (32, 2, TerrainLayout.OUT_NE, TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT_W     (30, 3, TerrainLayout.OUT_W,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT       (31, 3, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	DIRT_HOLE_OUT_E     (32, 3, TerrainLayout.OUT_E,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT_SW    (30, 4, TerrainLayout.OUT_SW, TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT_S     (31, 4, TerrainLayout.OUT_S,  TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_OUT_SE    (32, 4, TerrainLayout.OUT_SE, TerrainLayout.HOLE_DIRT),
	DIRT_HOLE_1         (30, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	DIRT_HOLE_2         (31, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE),
	DIRT_HOLE_3         (32, 5, TerrainLayout.FLAT,   TerrainLayout.HOLE);

	public static MapTile DEFAULT = GRASS_OUT;

	/**
	 * Since tile layouts are nearly entirely consistent between Terrains, this
	 * is a class to hold the various layouts.
	 */
	private static class TerrainLayout {
		public static final Terrain[] GRASS = { Terrain.GRASS },
									  WATER = { Terrain.WATER },
									  DIRT  = { Terrain.DIRT  },
									  ROCK  = { Terrain.ROCK  },
									  LAVA  = { Terrain.LAVA  },
									  HOLE  = { Terrain.HOLE  },
									  BRACKISH = { Terrain.BRACKISH };

		public static final Terrain[] GRASS_DIRT  = { Terrain.GRASS, Terrain.DIRT },
									  WATER_GRASS = { Terrain.WATER, Terrain.GRASS },
									  WATER_DIRT  = { Terrain.WATER, Terrain.DIRT },
									  ROCK_LAVA   = { Terrain.ROCK, Terrain.LAVA },
									  LAVA_ROCK   = { Terrain.LAVA, Terrain.ROCK },
									  DIRT_ROCK   = { Terrain.DIRT, Terrain.ROCK },
									  HOLE_ROCK   = { Terrain.HOLE, Terrain.ROCK },
									  HOLE_DIRT   = { Terrain.HOLE, Terrain.DIRT },
									  HOLE_GRASS  = { Terrain.HOLE, Terrain.GRASS },
									  BRACKISH_GRASS = { Terrain.BRACKISH, Terrain.GRASS };

		public static final int[][] FLAT   = {{0}},
								    IN_NW  = {
											{0, 0, 0, 0},
											{0, 0, 0, 0},
											{0, 0, 0, 1},
											{0, 0, 1, 1}},
								    IN_NE  = {
								    		{0, 0, 0, 0},
								    		{0, 0, 0, 0},
								    		{1, 0, 0, 0},
								    		{1, 1, 0, 0}},
								    IN_SW  = {
								    		{0, 0, 1, 1},
								    		{0, 0, 0, 1},
								    		{0, 0, 0, 0},
								    		{0, 0, 0, 0}},
								    IN_SE  = {
								    		{1, 1, 0, 0},
								    		{1, 0, 0, 0},
								    		{0, 0, 0, 0},
								    		{0, 0, 0, 0}},
								    OUT_NW = {
								    		{1, 1, 1, 1},
								    		{1, 1, 1, 1},
								    		{1, 1, 1, 0},
								    		{1, 1, 0, 0}},
								    OUT_N  = {
								    		{1},
								    		{0}},
								    OUT_NE = {
								    		{1, 1, 1, 1},
								    		{1, 1, 1, 1},
								    		{0, 1, 1, 1},
								    		{0, 0, 1, 1}},
								    OUT_E  = { {0, 1} },
								    OUT_W  = { {1, 0} },
								    OUT_SW = {
								    		{1, 1, 0, 0},
								    		{1, 1, 1, 0},
								    		{1, 1, 1, 1},
								    		{1, 1, 1, 1}},
								    OUT_S  = {
								    		{0},
								    		{1}},
								    OUT_SE = {
								    		{0, 0, 1, 1},
								    		{0, 1, 1, 1},
								    		{1, 1, 1, 1},
								    		{1, 1, 1, 1}};
	}

	/**
	 * Convert a Tile value into a String.
	 */
	public static String serialize(MapTile value) {
		return value.name();
	}

	/**
	 * Convert a string to a Tile
	 */
	public static MapTile deserialize(String name) {
		return MapTile.valueOf(name);
	}

	private Tile tile;
	private int[][] layout;
	private Terrain[] terrain;

	private MapTile(int i, int j, int[][] terrainLayoutIdx, Terrain[] terrain) {
		this(new Tile(i*32, j*32, 32, 32), terrainLayoutIdx, terrain);
	}

	/**
	 * Create a new MapTile. There are over 200 types, so these are created
	 * statically as Enums rather than subclasses.
	 * @param tile A Tile that describes the image
	 * @param layout A matrix of indexes into terrain parameter describing the
	 * layout of the terrain.
	 * @param terrain An array of Terrain values, describing possible types of
	 * terrain
	 */
	private MapTile(Tile tile, int[][] layout, Terrain[] terrain) {
		this.tile = tile;
		this.terrain = terrain;
		this.layout = layout;
	}

	/**
	 * Given a relative x and a relative y coordinate (origin at the upper left),
	 * determines the type of terrain at that location.
	 * It is the caller's responsibility to ensure that the coordinates are
	 * within the tile bounds.
	 */
	public Terrain getTerrain(int x, int y) {
		return terrain[layout[y*layout.length/tile.getHeight()][x*layout[0].length/tile.getWidth()]];
	}

	public Tile getTile() { return tile; }
}
