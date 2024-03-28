import typescript from '@rollup/plugin-typescript';

// Har fjernet minifying foreløpig
// import terser from '@rollup/plugin-terser';

export default {
    input: {
        "suite/hjem": 'src/js/suite/hjem.ts',
        "suite/ansatt": 'src/js/suite/ansatt.ts',
        "suite/personal": 'src/js/suite/personal.ts',
        "suite/rapporter": 'src/js/suite/rapporter.ts',
    },
    output: {
        dir: 'src/main/resources/static/js',
        format: 'es',
        sourcemap: true,
    },
    plugins: [
        typescript(),
        // terser(),
    ]
};