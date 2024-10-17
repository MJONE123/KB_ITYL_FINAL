<template>
  <div :class="[containerClass, 'w-full max-w-md']">
    <button
      v-for="option in dataOptions"
      :key="option"
      @click="onSelectOption(option)"
      :class="[
        buttonClass,
        'py-3 px-6 bg-gray-300 text-font-color hover:bg-kb-yellow-2 rounded-[15px] transition duration-300 disabled:opacity-50',
        selectedOption === option
          ? 'bg-kb-yellow-2 border-inherit'
          : 'bg-gray-200 hover:bg-kb-yellow-2'
        ]"
    >
      {{ option }}
    </button>
  </div>
</template>

<script setup>
  import { computed } from 'vue';

  const props = defineProps({
    dataOptions: Array,
    currentQuestionIndex: Number,
    questionIndex: Number,
    selectedOption: String,
  })

  const emit = defineEmits(['select-option']);

  const isCompactLayout = computed(() => props.dataOptions.length <= 5);

  const containerClass = computed(() =>
    isCompactLayout.value
      ? 'space-y-6'
      : 'grid grid-cols-2 gap-6'
  );

  const buttonClass = computed(() => 
    isCompactLayout.value
      ? 'w-full' : ''
  );

  const onSelectOption = (option) => {
    emit('select-option', props.questionIndex, option);
  };
</script>

<style scope>

</style>
