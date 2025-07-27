package net.aquamine.api.advancements

/**
 * Describes the frame around the Advancement.
 * Also describes the type of advancement it is for "toast" notifications.
 */
enum class FrameType {

    /**
     * A simple rounded square as the frame.
     */
    TASK,

    /**
     * A square with a outward rounded edge on the top and bottom as the frame.
     */
    GOAL,

    /**
     * A spike in all 8 directions as the frame.
     */
    CHALLENGE
}
