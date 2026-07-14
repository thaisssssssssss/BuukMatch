import { motion } from "framer-motion";
import { useMemo } from "react";

export default function FloatingIcons({ icon }) {
    const icons = useMemo(
        () =>
            Array.from({ length: 10 }, () => ({
                x: Math.random() * 120 - 60,
                delay: Math.random() * 0.3,
                duration: 1.2 + Math.random() * 0.5,
                size: 24 + Math.random() * 16,
            })),
        []
    );

    return (
        <>
            {icons.map((item, i) => (
                <motion.div
                    key={i}
                    initial={{
                        opacity: 0,
                        scale: 0.5,
                        y: 0,
                        x: 0,
                    }}
                    animate={{
                        opacity: [0, 1, 1, 0],
                        y: -250,
                        x: item.x,
                        scale: 1.2,
                    }}
                    transition={{
                        duration: item.duration,
                        delay: item.delay,
                        ease: "easeOut",
                    }}
                    style={{
                        position: "absolute",
                        bottom: 40,
                        left: "50%",
                        fontSize: item.size,
                        pointerEvents: "none",
                        zIndex: 999,
                    }}
                >
                    {icon}
                </motion.div>
            ))}
        </>
    );
}