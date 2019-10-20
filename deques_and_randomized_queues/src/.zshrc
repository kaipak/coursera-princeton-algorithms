__conda_setup="$('/Users/kai/anaconda3/bin/conda' 'shell.bash' 'hook' 2> /dev/null)"
if [ $? -eq 0 ]; then
    eval "$__conda_setup"
else
    if [ -f "/Users/kai/anaconda3/etc/profile.d/conda.sh" ]; then
        . "/Users/kai/anaconda3/etc/profile.d/conda.sh"
    else
        export PATH="/Users/kai/anaconda3/bin:$PATH"
    fi
fi
unset __conda_setup
