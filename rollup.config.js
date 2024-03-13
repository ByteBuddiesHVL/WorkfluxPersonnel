import typescript from '@rollup/plugin-typescript';

// Har fjernet minifying foreløpig
// import terser from '@rollup/plugin-terser';

export default {
    input: {
        test: 'src/js/test.ts',
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