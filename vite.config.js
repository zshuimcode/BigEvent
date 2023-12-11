import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// 自动导入 elementPlus
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      // importStyle: 'scss'
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
  server: {
    proxy: {
      "/firstapi": {
        target: "http://192.168.3.120:8080",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/firstapi/, ""),
      },
      "/api1": {
        target: "https://smms.app/api/v2",
        changeOrigin: true,
        secure: true,
        rewrite: (path) => path.replace(/^\/api1/, ""),
      },
    },
  },
  //  css: {
  //   preprocessorOptions: {
  //     scss: {
  //       additionalData: `
  //       @use "@/styles/element/index.scss" as *;
  //       @use "@/styles/var.scss" as *;
  //       `
  //     }
  //   }
  // },
});
