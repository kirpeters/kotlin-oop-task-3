/**
 * ================================================
 * Task 2 - Garden Gnomes on the Steps
 *
 * The gnomes have found a few home on some steps
 *
 *    /\
 *   ('')
 * __{__}__   /\
 *    #1  |  (oo)
 *        |__{__}__   /\
 *            #2  |  (xx)
 *                |__{__}__
 *                    #3  |
 *
 * Gnomes are placed on the steps in the garden.
 * The top step is #1, the lowest step is #5
 * - Only one gnome can be on a step at a time
 * - Gnomes can be shifted to different steps,
 *   but only if they are unoccupied
 * - Gnomes can be moved up and down the steps,
 *   but not above the top step (1), nor below the
 *   lowest (5)
 * - If Gnomes are moving up/down, and the target
 *   step is occupied, they take the step before
 * ================================================
 */


fun main() {
    println("Gnomes on Steps")
    println("------------------------")

    // Instantiate steps
    val steps = Steps()
    steps.show()

    check(steps.gnomeCount() == 0)

    println("------------------------")
    // Instantiate gnomes
    val jim = Gnome("Jim")
    val sam = Gnome("Sam")
    val amy = Gnome("Amy")



    println("------------------------")
    // Place gnomes on steps
    steps.placeGnome(1, jim)    // Should be fine
    steps.placeGnome(4, sam)
    steps.show()

    check(steps.gnomeCount() == 2)

    check(steps.stepNumOfGnome(jim) == 1)
    check(steps.stepNumOfGnome(sam) == 4)
    check(steps.stepNumOfGnome(amy) == 0)          // Not placed yet

    check(steps.gnomeOnStep(1) == jim)
    check(steps.gnomeOnStep(4) == sam)
    check(steps.gnomeOnStep(5) == null)   // Empty step

    println("------------------------")
    // Try to place gnomes off the steps
    steps.placeGnome(6, amy)    // Should do nothing
    steps.show()
    steps.placeGnome(0, amy)    // Should do nothing
    steps.show()

    check(steps.gnomeCount() == 2)
    check(steps.stepNumOfGnome(amy) == 0)

    println("------------------------")
    // Try to place a gnome on an occupied step
    steps.placeGnome(1, amy)    // Should swap out Jim
    steps.show()

    check(steps.gnomeCount() == 2)
    check(steps.stepNumOfGnome(amy) == 1)
    check(steps.stepNumOfGnome(jim) == 0)

    println("------------------------")
    // Try to clear a gnome from a step
    steps.clearStep(4)          // Should remove Sam
    steps.show()

    check(steps.gnomeCount() == 1)
    check(steps.stepNumOfGnome(sam) == 0)

    println("------------------------")
    // Try to remove a specific gnome
    steps.removeGnome(amy)          // Should remove it
    steps.show()

    check(steps.gnomeCount() == 0)
    check(steps.stepNumOfGnome(amy) == 0)

    println("------------------------")
    // Try to remove a specific gnome not on steps
    steps.removeGnome(amy)          // Should do nothing as not on steps
    steps.show()

    println("------------------------")
    // Try to place a gnome that is already on steps
    steps.placeGnome(3, jim)    // Should place it ok
    steps.show()

    steps.placeGnome(5, jim)    // Should move it down
    steps.show()

    steps.placeGnome(5, jim)    // Should do nothing
    steps.show()

    check(steps.gnomeCount() == 1)
    check(steps.stepNumOfGnome(jim) == 5)


}


/**
 * Steps class
 */
class Steps() {
    // Steps list, either holding a gnome or null
    val steps = mutableListOf<Gnome?>()

    val STEPCOUNT = 5

    init {
        println("Setting up steps...")
        // Add empty steps
        for (i in 1..STEPCOUNT) steps.add(null)
    }

    /**
     * Show the steps with the names of any gnomes
     * on them. If a step is empty, show no text
     * 1. Jim
     * 2.
     * 3. Sam
     * etc.
     */
    fun show() {
        var i = 1
        for (step in steps) {
            if(step != null) {
                println("$i. + $step")
            }else{
                println("$i.")
            }

            i++
        }


    }

    /**
     * Return the number of gnomes on steps
     */
    fun gnomeCount(): Int {
        var count = 0
        for (step in steps) {
            if (step != null) {
                count += 1
            }

        }
        return count
    }

    /**
     * Place a given gnome on the given step (1-5)
     */
    fun placeGnome(step: Int, gnome: Gnome) {
        steps.add(step, gnome)

    }

    /**
     * Find a given gnome on the steps. Return the
     * step number (1-5) if found, or 0 if not
     */
    fun stepNumOfGnome(gnome: Gnome): Int {
        var count: Int = 1
        for (step in steps){
            if (step == gnome) {
                return count
            }
            count++
        }
        return 0
    }

    /**
     * Return the gnome on the given step (1-5),
     * or null if out of range or empty
     */
    fun gnomeOnStep(stepNum: Int): Gnome? {
        return null
    }

    /**
     * Clear any gnomes off the given step (1-5)
     */
    fun clearStep(step: Int) {

    }

    /**
     * Remove a given gnome from the steps
     */
    fun removeGnome(gnome: Gnome) {

    }
}


/**
 * Gnome class
 */
class Gnome(val name: String) {
    init {
        println("Creating a gnome... $name")
    }
}

