module.exports = {
    env: {
        browser: true,
        es2021: true
    },
    extends: [
        'eslint:recommended',
        '@vue/typescript/recommended',
        'plugin:@typescript-eslint/recommended',
        'plugin:@typescript-eslint/recommended-requiring-type-checking',
        'prettier',
    ],
    parser: '@typescript-eslint/parser',
    parserOptions: {
        ecmaVersion: 13,
        sourceType: 'module',
        // eslint-disable-next-line no-undef
        tsconfigRootDir: __dirname,
        project: ['./tsconfig.eslint.json'],
    },
    plugins: [
        'vue',
        '@typescript-eslint'
    ],
    rules: {
        'no-console': 'warn',
        'no-extra-semi': 'warn',
        'no-undef': 'warn',
        'quotes': [
            'warn', 'single'
        ],
        'space-before-blocks': [
            'warn', {
                'functions': 'always'
            }
        ],
        '@typescript-eslint/no-unsafe-assignment': 'warn',
        '@typescript-eslint/no-unsafe-call': 'warn',
        '@typescript-eslint/no-unsafe-member-access': 'warn',
        '@typescript-eslint/no-unsafe-return': 'warn'
    },
};
